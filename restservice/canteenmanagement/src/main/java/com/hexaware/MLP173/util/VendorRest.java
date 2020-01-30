package com.hexaware.MLP173.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP173.model.Vendor;
import com.hexaware.MLP173.factory.VendorFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/vendor")
public class VendorRest {
  /**
   * Returns vendor details.
   * @return the vendor details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Vendor[] listVendor() {
    final Vendor[] vendor = VendorFactory.showVendor();
    return vendor;
  }
}
