package me.choi.inflearnthejavatest;

import me.choi.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(FindSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {


    //값을 수정해서 쓸수있씀
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Slow
    @Order(2)
    @Disabled
    public void create() {
        String env = System.getenv("TEST_ENV");
        assumeTrue("test".equalsIgnoreCase(env));
    }

    @Test
    public void slowTest() throws InterruptedException {
        Thread.sleep(2000L);
    }

    @Order(3)
    @DisabledOnOs(OS.MAC)
    @DisplayName("반복 실행")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    public void createFor(RepetitionInfo repetitionInfo) {
        System.out.println(Math.random() + " : " + repetitionInfo.getCurrentRepetition() + " - " + repetitionInfo.getTotalRepetitions());
    }

    private String[] str = {"날씨가", "많이", "더워잉", "호에에엥"};



    @Order(1)
    //using AutoConverter
    @DisplayName("반복 실행")
    @ParameterizedTest(name = "{index} {displayName} index = {0}")
    @CsvSource({"'자바 테스트', 10", "'자바 테스트문', 20"})
    public void createLoop(ArgumentsAccessor argumentsAccessor) {
        System.out.println(argumentsAccessor.getString(0) + " : " + argumentsAccessor.getInteger(1));
    }


    @ParameterizedTest(name = "{index} {displayName} index = {0}")
    @CsvSource({"'String And Integer', 20", "'외 않되?', 30"})
    public void test_2(ArgumentsAccessor argumentsAccessor){
        System.out.println(argumentsAccessor.getString(0) + " : " + argumentsAccessor.getInteger(1));
    }
    //ArgumentAccessor 여러 args를 받는 경우 사용 가능하다
//    static class StudyAggregator implements ArgumentsAggregator {
//        @Override
//        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
//            Study study = new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
//            return study;
//        }
//    }

    //SimpleArgumentConverter 하나만 변환 가능
//    static class StudyConverter extends SimpleArgumentConverter {
//        @Override
//        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
//            assertEquals(Study.class, aClass, "Can Only conver to Study");
//            return new Study(Integer.parseInt(o.toString()));
//        }
//    }


    @Fast
    public void delete_1() {
        System.out.println("delete_number_1");
    }

    @Fast
    public void delete_2() {
        System.out.println("delete_number_2");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before 단 한번!");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after 단 한번!");
    }
}