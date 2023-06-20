package br.com.lamppit.teste.health;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@ApiOperation(value = "Endpoint teste")
public class HealthController {
    @GetMapping
    public String health() {
        return "Hi!";
    }
}
