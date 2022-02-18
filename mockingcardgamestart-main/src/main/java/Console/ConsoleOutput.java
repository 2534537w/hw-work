package Console;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput {
    protected List<String> storeOutput = new ArrayList<String>();

    public void output(String message){
        storeOutput.add(message);
        System.out.println(message);
    }

    public List<String> getStoreOutput(){
        return storeOutput;
    }


}
