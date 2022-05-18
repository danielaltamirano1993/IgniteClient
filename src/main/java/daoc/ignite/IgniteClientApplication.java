package daoc.ignite;

import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientTransaction;
import org.apache.ignite.client.ClientTransactions;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IgniteClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IgniteClientApplication.class, args);
	}

	@Autowired
	private IgniteClient client;//creacion del cliente

	@Override
	public void run(String... args) throws Exception {
		ClientCache<String, Integer> cache = client.getOrCreateCache("miCache");
		ClientTransactions cts = client.transactions();
		
		 
		
		
		try (ClientTransaction ct = cts.txStart()) {
			//txStart --> 
			Integer counter = cache.get("counter");
			if(counter == null) {
				cache.put("counter", 1);
			} else {
				cache.put("counter", counter + 1);
			}
			ct.commit();
		}		
		
		System.out.println(cache.get("uno"));
		System.out.println(cache.get("dos"));
		System.out.println(cache.get("counter"));
	}
	
}
