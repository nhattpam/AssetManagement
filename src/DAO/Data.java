
package DAO;

import Core.Asset;
import Core.Borrow;
import Core.Employee;
import Core.Request;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nhattpam
 */
public abstract class Data {

    abstract List<Asset> assetLoadData(String s) throws IOException;

    abstract public List<Employee> employLoadData(String str) throws IOException;

    abstract public List<Borrow> borrowLoadData(String str) throws IOException;

    abstract public List<Request> requestLoadData(String str) throws IOException;

}
