package test;

import java.util.UUID;

import org.junit.Test;

import com.sidc.utils.exception.SiDCException;

public class test {

	@Test
	public void test() throws SiDCException {
//		RcuModeDeviceRequest aa = new RcuModeDeviceRequest(1);
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(aa));
//
//		final RcuModeDeviceRequest entity = (RcuModeDeviceRequest) APIParser.getInstance().parses(gson.toJson(aa),
//				RcuModeDeviceRequest.class);
//		
//		System.out.println(entity);
		
		final UUID myuuid = UUID.randomUUID();
		String highbits = String.valueOf(myuuid.getMostSignificantBits()).replaceAll("-", "");
		long lowbits = myuuid.getLeastSignificantBits();
		System.out.println("My UUID is: " + highbits + " " + lowbits);
	}

}
