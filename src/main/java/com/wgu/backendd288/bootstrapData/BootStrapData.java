package com.wgu.backendd288.bootstrapData;

import com.wgu.backendd288.dao.CountryRepository;
import com.wgu.backendd288.dao.CustomerRepository;
import com.wgu.backendd288.dao.DivisionRepository;
import com.wgu.backendd288.entities.Country;
import com.wgu.backendd288.entities.Customer;
import com.wgu.backendd288.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    private final DivisionRepository divisionRepository;

    private final CountryRepository countryRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository, CountryRepository countryRepository){
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        if (customerRepository.count() == 1) {

            Country USA = new Country();
            USA.setCountry_name("USA");
            USA.setCreate_date(new Date());
            USA.setLast_update(new Date());

            countryRepository.save(USA);

            Division div1 = new Division();
            Division div2 = new Division();
            Division div3 = new Division();
            Division div4 = new Division();
            Division div5 = new Division();

            div1.setCountry(USA);
            div2.setCountry(USA);
            div3.setCountry(USA);
            div4.setCountry(USA);
            div5.setCountry(USA);

            div1.setDivision_name("Red");
            div2.setDivision_name("Blue");
            div3.setDivision_name("White");
            div4.setDivision_name("Green");
            div5.setDivision_name("Yellow");

            div1.setCreate_date(new Date());
            div1.setLast_update(new Date());
            div2.setCreate_date(new Date());
            div2.setLast_update(new Date());
            div3.setCreate_date(new Date());
            div3.setLast_update(new Date());
            div4.setCreate_date(new Date());
            div4.setLast_update(new Date());
            div5.setCreate_date(new Date());
            div5.setLast_update(new Date());

            divisionRepository.save(div1);
            divisionRepository.save(div2);
            divisionRepository.save(div3);
            divisionRepository.save(div4);
            divisionRepository.save(div5);


            Customer first = new Customer();
            first.setFirstName("Burger");
            first.setLastName("King");
            first.setAddress("123 street");
            first.setPostal_code("09876");
            first.setPhone("1234567890");
            first.setDivision(div1);
            first.setCreate_date(new Date());
            first.setLast_update(new Date());

            Customer second = new Customer();
            second.setFirstName("Mc");
            second.setLastName("Donalds");
            second.setAddress("234 avenue");
            second.setPostal_code("13243");
            second.setPhone("0987654321");
            second.setDivision(div2);
            first.setCreate_date(new Date());
            first.setLast_update(new Date());

            Customer third = new Customer();
            third.setFirstName("Five");
            third.setLastName("Guys");
            third.setAddress("345 lane");
            third.setPostal_code("35764");
            third.setPhone("7562947251");
            third.setDivision(div3);
            first.setCreate_date(new Date());
            first.setLast_update(new Date());


            Customer fourth = new Customer();
            fourth.setFirstName("Walter");
            fourth.setLastName("White");
            fourth.setAddress("456 Crystal Road");
            fourth.setPostal_code("87654");
            fourth.setPhone("8167778888");
            fourth.setDivision(div4);
            first.setCreate_date(new Date());
            first.setLast_update(new Date());

            Customer fifth = new Customer();
            fifth.setFirstName("Pierre");
            fifth.setLastName("Laplace");
            fifth.setAddress("765 transform drive");
            fifth.setPostal_code("99888");
            fifth.setPhone("1112223333");
            fifth.setDivision(div5);
            first.setCreate_date(new Date());
            first.setLast_update(new Date());

            customerRepository.save(first);
            customerRepository.save(second);
            customerRepository.save(third);
            customerRepository.save(fourth);
            customerRepository.save(fifth);

        }
    }
}
