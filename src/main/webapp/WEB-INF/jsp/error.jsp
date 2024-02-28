<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
</head>
    <body>
        <h1>載入失敗</h1>
        <button onclick="goToHomePage()">返回首頁</button>

        <script>
            // 定義一個函數來導航到首頁
            function goToHomePage() {
                // 使用 JavaScript 的 window.location.href 來導航到首頁的 URL
                window.location.href = "/";
            }
        </script>
    </body>
</html>