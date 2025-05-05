package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FixChecksumGeneratorTest {

    @Test
    void shouldThrowNullPointerExceptionWhenTheMessageIsNull() {

        FixChecksumGenerator fixChecksumGenerator = new FixChecksumGenerator();
        Assertions.assertThrows(
                NullPointerException.class,
                () -> fixChecksumGenerator.getChecksum(null));
    }

    @ParameterizedTest
    @MethodSource("providedFixMessages")
    void shouldReturnTheFixProtocolChecksum(String message, int checksum) {

        FixChecksumGenerator fixChecksumGenerator = new FixChecksumGenerator();
        Assertions.assertEquals(checksum, fixChecksumGenerator.getChecksum(message));
    }

    private static Stream<Arguments> providedFixMessages() {
        return Stream.of(
                Arguments.of(
                        "8=FIX.4.4|9=102|35=D|34=1|49=XXX|52=20200206-21:15:13.000|" +
                                "56=YYY|11=321|41=123|54=B|55=LNUX|58=This is a new message.|10=179|",
                        179),
                Arguments.of(
                        "8=FIX.4.4|9=148|35=D|34=1080|49=TESTBUY1|52=20180920-18:14:19.508|" +
                                "56=TESTSELL1|11=636730640278898634|15=USD|21=2|38=7000|40=1|54=1|55=MSFT|" +
                                "60=20180920-18:14:19.492|10=092|",
                        92),
                Arguments.of(
                        "8=FIX.4.4|9=289|35=8|34=1090|49=TESTSELL1|52=20180920-18:23:53.671|" +
                                "56=TESTBUY1|6=113.35|11=636730640278898634|14=3500.0000000000|15=USD|" +
                                "17=20636730646335310000|21=2|31=113.35|32=3500|37=20636730646335310000|" +
                                "38=7000|39=1|40=1|54=1|55=MSFT|60=20180920-18:23:53.531|150=F|151=3500|" +
                                "453=1|448=BRK2|447=D|452=1|10=151|",
                        151
                )
        );
    }
}