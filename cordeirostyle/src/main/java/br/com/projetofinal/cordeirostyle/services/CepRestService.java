package br.com.projetofinal.cordeirostyle.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.projetofinal.cordeirostyle.dtos.CepDto;


	@Service
	public class CepRestService {

		public CepDto findUserByCepFromViaCep(String cep) {
			RestTemplate restTemplate = new RestTemplate(); // RestTemplate = é um classe que faz com que a nossa Api
															// interaja com uma Api externa

			String uri = "https://viacep.com.br/ws/{cep}/json/"; // Uniform Resource Identifier (uri), caminho p/ a Api
																// externa

			/* Map = interface q representa uma coleção de pares chave-valor, onde cada chave
			 	está associada a apenas um valor*/
			// HashMap = é uma das implementações do Map, ele usa a tabela Hash q associa
			// chave a valor para fazer buscas rápidas
			Map<String, String> params = new HashMap<String, String>(); /* cria um mapa chamado params pra armazenar um
																		 	valor do lugar reservado (placeholder) na url*/

			params.put("cep", cep); //coloca a chave "id" e o valor id dentro do mapa params

			/* getForObject é um método do RestTemplate que faz uma requisição HTTP GET e
			   guarda o valor na variável dto. Ele usa como parâmetros a uri, a classe 
			   FakeStoreUserDto para deserializar a resposta JSON em um objeto dessa classe, 
			   e o params para substituir o placeholder {id} na uri com o valor correspondente. */
			CepDto dto = restTemplate.getForObject(uri, CepDto.class, params);

			return dto; // retorna o dto
		}

}
