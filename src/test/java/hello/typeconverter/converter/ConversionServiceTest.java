package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService(){
        //등록
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new StringToIntegerConverter());
        defaultConversionService.addConverter(new IntegerToStringConverter());
        defaultConversionService.addConverter(new StringToIpPortConverter());
        defaultConversionService.addConverter(new IpPortToStringConverter());

        assertThat(defaultConversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(defaultConversionService.convert(10, String.class)).isEqualTo("10");

        IpPort result1 = defaultConversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result1).isEqualTo(new IpPort("127.0.0.1", 8080));

        String result2 = defaultConversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(result2).isEqualTo("127.0.0.1:8080");
    }
}
