import java.util.Objects;
public class Student {
    private String name; private int marks;
    public Student(String name,int marks){setName(name);setMarks(marks);} public String getName(){return name;} public int getMarks(){return marks;}
    public void setName(String name){Objects.requireNonNull(name,"name");if(name.isBlank())throw new IllegalArgumentException("name required");this.name=name.trim();}
    public void setMarks(int marks){if(marks<0||marks>100)throw new IllegalArgumentException("marks must be 0..100");this.marks=marks;}
    public char getGrade(){return marks>=80?'A':marks>=70?'B':marks>=60?'C':'F';} // EXPLAIN: grade is derived, so it cannot become stale.
    public static void main(String[] a){var s=new Student("Tariro",84);System.out.println(s.name+": "+s.marks+" -> "+s.getGrade());}
}
