public class Calculator {
    private String name; //Object name
    public float addition(float A, float B){ //Adds two numbers
        return A+B;
    }
    public float subtraction(float A, float B){ //Subtracts two numbers
        return A-B;
    }
    public float multiplication(float A, float B){ //Multiplies two numbers
        return A*B;
    }
    public void setname(String N){ //Sets the object name
        this.name=N;
    }
    public String getname(){ //Gets the object's name
        return name;
    }
}

