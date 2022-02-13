package br.com.wit.calculadora.controller;

import br.com.wit.calculadora.domain.model.ResponseOperation;
import br.com.wit.calculadora.services.CalculadoraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    @GetMapping("sum")
    public ResponseEntity sum(@RequestParam(value = "a", required = true)BigDecimal a, @RequestParam(value = "b", required = true)BigDecimal b){
        try {
            return calculadoraService.calculateSum(a,b);
        } catch (Exception e) {
            log.error("Sum rest method error {}",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("subtract")
    public ResponseEntity subtract(@RequestParam(value = "a", required = true)BigDecimal a, @RequestParam(value = "b", required = true)BigDecimal b){
        try {
            return calculadoraService.calculateSubtraction(a,b);
        } catch (Exception e) {
            log.error("Subtract rest method error {}",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @GetMapping("divide")
    public ResponseEntity divide(@RequestParam(value = "a", required = true)BigDecimal a, @RequestParam(value = "b", required = true)BigDecimal b){
        try {
            return calculadoraService.calculateDivision(a,b);
        } catch (Exception e) {
            log.error("Division rest method error {}",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("multiply")
    public ResponseEntity multiply(@RequestParam(value = "a", required = true)BigDecimal a, @RequestParam(value = "b", required = true)BigDecimal b){
        try {
            return calculadoraService.calculateMultiplication(a,b);
        } catch (Exception e) {
            log.error("Multiply rest method error {}",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
