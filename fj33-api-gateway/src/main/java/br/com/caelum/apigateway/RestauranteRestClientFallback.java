package br.com.caelum.apigateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
class RestauranteRestClientFallback implements RestauranteRestClient {

  @Override
  public Map<String,Object> porId(Long id) {
    Map<String,Object> resultado = new HashMap<>();
    resultado.put("id", id);
    return resultado;
  }

}