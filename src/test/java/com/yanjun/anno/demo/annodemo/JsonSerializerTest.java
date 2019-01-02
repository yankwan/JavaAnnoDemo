package com.yanjun.anno.demo.annodemo;

import com.yanjun.anno.demo.annodemo.anno.TestIt;
import com.yanjun.anno.demo.annodemo.anno.TesterInfo;
import com.yanjun.anno.demo.annodemo.annoparser.AnnoParser;
import com.yanjun.anno.demo.annodemo.data.Car;
import com.yanjun.anno.demo.annodemo.demoserializer.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

@TesterInfo(
		priority = TesterInfo.Priority.HIGH,
		tags = {"test", "annotations"}
)
@Slf4j
public class JsonSerializerTest {
	
	@Test(expected = NullPointerException.class)
	public void serializeNullObjectEnsureNullPointerExceptionThrown() throws Exception {
		new JsonSerializer().serialize(null);
	}

	@Test
	public void serializeCarObjectEnsureCorrectOutputJson() throws Exception {
		JsonSerializer serializer = new JsonSerializer();
		Car testCar = new Car("Ford", "F150", "2018");
		String json = serializer.serialize(testCar);
		assertThat(json, isExpectedCarJson(testCar));
	}

	@Test
	public void joyousShowCar() {
		AnnoParser annoParser = new AnnoParser();
		Car car = new Car("Audit", "A8", "2018");
		annoParser.parse(car);
	}


	@Test
	@TestIt(enabled = true)
	public void testItAnno() {
		AnnoParser annoParser = new AnnoParser();
		annoParser.testItParse(JsonSerializerTest.class);
		log.info("TestIt Annotation");
	}
	
	private static Matcher<String> isExpectedCarJson(Car car) {
		return isOneOf(
			"{\"manufacturer\":\"" + car.getMake() + "\",\"model\":\"" + car.getModel() + "\"}",
			"{\"model\":\"" + car.getModel() + "\",\"manufacturer\":\"" + car.getMake() + "\"}"
		);
	}
}
