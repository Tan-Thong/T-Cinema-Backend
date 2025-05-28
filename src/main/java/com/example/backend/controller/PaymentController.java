package com.example.backend.controller;

import com.example.backend.config.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/create")
    public ResponseEntity<?> createPayment(
            @RequestParam String orderId,
            @RequestParam int amount, // đơn vị VND
            HttpServletRequest request
    ) throws UnsupportedEncodingException {

        String vnp_TmnCode = "8DTLLN2T";
        String vnp_HashSecret = "7H9RVDE4TXHL7M7FYA2J90KDA24AFREV";
        String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String vnp_ReturnUrl = "https://t-cinema-frontend.vercel.app/payment/return";

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderInfo = "Thanh toan don hang " + orderId;
        String orderType = "other";
        String locale = "vn";
        String currCode = "VND";

        String vnp_TxnRef = orderId;
        String vnp_IpAddr = request.getRemoteAddr();
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100)); // nhân 100
        vnp_Params.put("vnp_CurrCode", currCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", locale);
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        // Sắp xếp theo key
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = vnp_Params.get(fieldName);
            if ((value != null) && (value.length() > 0)) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                query.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
            }
        }
        // Xóa ký tự `&` cuối
        hashData.setLength(hashData.length() - 1);
        query.setLength(query.length() - 1);

        String secureHash = VNPayUtils.hmacSHA512(vnp_HashSecret, hashData.toString());

        query.append("&vnp_SecureHash=").append(secureHash);

        String paymentUrl = vnp_Url + "?" + query.toString();
        return ResponseEntity.ok(Collections.singletonMap("paymentUrl", paymentUrl));
    }

    @GetMapping("/return")
    public ResponseEntity<?> paymentReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_HashSecret = "YOUR_HASH_SECRET";
        String vnp_SecureHash = fields.remove("vnp_SecureHash");

        // Sắp xếp lại và xác minh chữ ký
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = fields.get(fieldName);
            if ((value != null) && (value.length() > 0)) {
                hashData.append(fieldName).append('=').append(value).append('&');
            }
        }
        hashData.setLength(hashData.length() - 1);

        String checkHash = VNPayUtils.hmacSHA512(vnp_HashSecret, hashData.toString());

        if (checkHash.equals(vnp_SecureHash)) {
            String responseCode = fields.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok("Thanh toán thành công!");
            } else {
                return ResponseEntity.ok("Thanh toán thất bại!");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid hash");
        }
    }
}
