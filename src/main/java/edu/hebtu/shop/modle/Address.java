package main.java.edu.hebtu.shop.modle;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class Address extends Model<Address> {
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Address.class);
    public static final Address addressDao = new Address();
}
