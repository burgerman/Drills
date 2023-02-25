package assignment.meo.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Age: 1-18
 *
 * status:
 *      0 -> Healthy
 *      1 -> Injured
 */
public class Koala {
    int age;
    int status;

    public Koala(int age, int status) {
        this.age = age;
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

    @Override
    public String toString() {
        return "Koala{" +
                "age=" + age +
                ", status=" + status +
                '}';
    }
}
