package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

   @Test
   public void testMyIp() {
      GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("193.106.171.155");
      assertEquals(geoIP.getCountryCode(), "CZE");
   }

   @Test
   public void testInvalidIp() {
      GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("193.106.171.999");
      assertEquals(geoIP.getCountryCode(), "CZE");
   }

}
