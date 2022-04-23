package javatpoint.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	CurrencyExchangeServiceProxy currency_ex_proxy;
//	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
//			@PathVariable BigDecimal quantity) {
//		HashMap<String, String> uriVariable = new HashMap();
//		uriVariable.put("from", from);
//		uriVariable.put("to", to);
//		try {
//			ResponseEntity<CurrencyConversionBean> forEntity = new RestTemplate().getForEntity(
//					"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
//					uriVariable);
//			CurrencyConversionBean body = forEntity.getBody();
//			return new CurrencyConversionBean(body.getId(), from, to, body.getConversionMultiple(), quantity,
//					quantity.multiply(body.getConversionMultiple()), body.getPort());
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/{quantity}")
	public CurrencyConversionBean getConvertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		try {
			CurrencyConversionBean body = currency_ex_proxy.convertCurrency(from, to);
			return new CurrencyConversionBean(body.getId(), from, to, body.getConversionMultiple(), quantity,
					quantity.multiply(body.getConversionMultiple()), body.getPort());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return null;

	}
}