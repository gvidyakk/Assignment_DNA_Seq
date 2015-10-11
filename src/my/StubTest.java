package my;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class StubTest {

	/*
	 * Declare harnesses that let you test a mapper, a reducer, and a mapper and
	 * a reducer working together.
	 */
	MapDriver<Object, Text, Text, Text> mapDriver;
	ReduceDriver<Text, Text, Text, Text> reduceDriver;
	MapReduceDriver<Object, Text, Text, Text, Text, Text> mapReduceDriver;

	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {

		/*
		 * Set up the mapper test harness.
		 */
		//StubMapper mapper = new StubMapper();
		StubMapper mapper = new StubMapper();
		mapDriver = new MapDriver<Object, Text, Text, Text>();
		mapDriver.setMapper(mapper);
		/*
		 * Set up the reducer test harness.
		 */
		StubReducer reducer = new StubReducer();
		reduceDriver = new ReduceDriver<Text, Text, Text, Text>();
		reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<Object, Text, Text, Text, Text, Text>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	/*
	 * Test the mapper.
	 */
	@Test
	public void testMapper() {

		/*
		 * For this test, the mapper's input will be "1 cat cat dog" TODO:
		 * implement
		 */
		mapDriver.setInputKey("1");
		mapDriver.setInputValue(new Text("Hello        is test"));
		List<Pair<Text, Text>> x;
		try {
			//System.out.println("I am at testmapper()");
			x = mapDriver.run();
			//assertEquals(x.size(), 3);
			assertEquals(x.get(0).getFirst().toString(), "this");
			assertEquals(x.get(1).getFirst().toString(), "is");
			assertEquals(x.get(2).getFirst().toString(), "test");
			
			for(Pair<Text, Text> p:x)
			{
				System.out.println("MAPPER: " + p.getFirst() + ": " + p.getSecond());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * Test the reducer.
	 */
	@Test
	public void testReducer() {

		/*
		 * For this test, the reducer's input will be "cat 1 1". The expected
		 * output is "cat 2". TODO: implement
		 */
		fail("Please implement test.");

	}

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() throws IOException {

		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
				"3.96124E+17 Think about the life you livin but don't think so hard it hurts Life is truly a gift, but at the same it is a curse Obey_Jony09")));
		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
				"teach the map and reduce class is fun.")));
		List<Pair<Text, Text>> output = mapReduceDriver.run();
		//assertEquals(12, output.size());
		for (Pair<Text, Text> p : output) {
			System.out.println(p.getFirst() + " - " + p.getSecond());
		}
	}
}

