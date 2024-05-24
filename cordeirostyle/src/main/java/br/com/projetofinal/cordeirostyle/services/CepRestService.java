package br.com.projetofinal.cordeirostyle.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.projetofinal.cordeirostyle.dtos.CepDto;


	@Service
	public class CepRestService {

		public CepDto findUserByCepFromViaCep(String cep) {
			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://viacep.com.br/ws/{cep}/json/";
			
			Map<String, String> params = new HashMap<String, String>();

			params.put("cep", cep);
			CepDto dto = restTemplate.getForObject(uri, CepDto.class, params);

			return dto;
		}

}
