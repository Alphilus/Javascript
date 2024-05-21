package com.example.demo.rest;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.demo.model.enums.UserRole.ADMIN;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        log.info("request: {}", request);
        // Kiem tra xem user co ton tai trong database khong
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email incorrect"));

        // Kiểm tra xem password có khớp với password trong database không
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Password incorrect");
        }

        // Lưu thông tin user vào trong session để sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }

    public User getCurrentUser() {
        return (User) session.getAttribute("currentUser");
    }

    public boolean isAdmin() {
        User user = getCurrentUser();
        return user != null && user.getRole().equals(ADMIN);
    }


}