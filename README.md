# DỊCH VỤ ĐĂNG KÝ KHÁM LẠI
- Dịch vụ đăng ký khám lại xây dựng bằng Spring boot và Eureka
- Dịch vụ bao gồm:
  + 1 service registry sử dụng eureka để quản lý, đặt tên cho các service
  + 1 utility service notification để gửi mail thông báo đăng ký khám lại thành công
  + 3 entity service: BenhNhan, BacSi, LichKham
- Cài đặt môi trường:
  + B1: Cài đặt spring tool suite
      https://spring.io/tools
  + B2: Cài đặt môi trường java
  + B3: Cài đặt MySQL
- Chạy chương trình:
  + B1: Start discovery server
  + B2: Start các service: Task service, Utility Service, Entity Service
  + B3: Chạy trên cổng 8761 để thấy các dịch vụ đang chạy 
        + http://localhost:8761/
  + B4: Chạy Client trên cổng 8086
        + http://localhost:8086/newDK
  + B5: Thực hiện đăng ký khám
