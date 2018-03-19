package pl.javastart.exercise.mockito;

public class Human {

    private String name;
    private int age;
    private String job;
    private int money;

    public Human() {}

    public Human(String name, int age, String job, int money) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.money = money;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
