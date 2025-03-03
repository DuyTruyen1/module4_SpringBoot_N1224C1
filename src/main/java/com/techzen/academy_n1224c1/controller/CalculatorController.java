package com.techzen.academy_n1224c1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/calculator")
    public ResponseEntity<String> calculate(
            @RequestParam(value = "firstNumber") String firstNumber,
            @RequestParam(value = "secondNumber") String secondNumber,
            @RequestParam(value = "operator") String operator) {

        // Kiểm tra firstNumber và secondNumber có phải là số không
        if(firstNumber.isEmpty() || secondNumber.isEmpty() || operator.isEmpty()) {
            return ResponseEntity.badRequest().body("FirstName and SecondName are empty");
        } else if (!isNumeric(firstNumber) || !isNumeric(secondNumber)) {
            return ResponseEntity.badRequest().body("First number and second number must be numeric values.");
        }


        double num1 = Double.parseDouble(firstNumber);
        double num2 = Double.parseDouble(secondNumber);
        double result;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return ResponseEntity.badRequest().body("Cannot divide by zero.");
                }
                result = num1 / num2;
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid operator");
        }

        return ResponseEntity.ok("Result: " + result);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
