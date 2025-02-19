package com.techzen.academy_n1224c1.dictionary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DictionaryController {
    private final Map<String, String> dictionary = new ConcurrentHashMap<>();

    public DictionaryController() {
        // Thêm nhiều từ vào từ điển
        dictionary.put("hello", "Xin Chào");
        dictionary.put("apple", "Quả Táo");
        dictionary.put("banana", "Quả Chuối");
        dictionary.put("orange", "Quả Cam");
        dictionary.put("lemon", "Quả Chanh");
        dictionary.put("melon", "Quả Dưa");
        dictionary.put("watermelon", "Quả Dưa Hấu");
        dictionary.put("grape", "Quả Nho");
        dictionary.put("pineapple", "Quả Dứa");
        dictionary.put("mango", "Quả Xoài");
        dictionary.put("peach", "Quả Đào");
        dictionary.put("pear", "Quả Lê");
        dictionary.put("strawberry", "Quả Dâu Tây");
        dictionary.put("blueberry", "Quả Việt Quất");
        dictionary.put("cherry", "Quả Anh Đào");
    }

    @GetMapping("/dictionary")
    public ResponseEntity<String> translate(@RequestParam(defaultValue = "") String name) {
        String input = name.trim().toLowerCase(); // Chuẩn hóa input

        if (input.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vui lòng nhập từ cần tra cứu.");
        }

        String translation = dictionary.get(input);
        if (translation != null) {
            return ResponseEntity.ok(translation);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy từ '" + name + "' trong từ điển.");
    }
}
