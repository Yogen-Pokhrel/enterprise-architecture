package bank.configuration;

import lombok.Getter;

@Getter
public enum Currency {
    USD("USD"),
    EUR("EUR");
    final String value;
    Currency(String currency){
        this.value = currency;
    }
}
