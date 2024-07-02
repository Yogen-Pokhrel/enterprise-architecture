package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		createPersonsAndPets();
		fetchPersons();
	}

	public void createPersonsAndPets() {
		System.out.println("Creating persons with pets ...");
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			Person person = new Person();
			person.setName("Person " + i);
			for(int j = 0; j < 10; j++) {
				Pet pet = new Pet();
				pet.setName("Pet " + j);
				person.addPet(pet);
			}
			personRepository.save(person);
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To insert 10,000 persons with pets it took "+timeElapsed+" ms in psql");
	}

	public void fetchPersons() {
		System.out.println("Fetching persons with pets ...");
		long start = System.currentTimeMillis();
		List<Person> personList = personRepository.findAll();
//		personList.forEach((e) -> System.out.println(e.getName() + " pets: " + e.getPets()));
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To fetch persons with pets it took "+timeElapsed+" ms in psql");
	}


}
