public class Printer {
    private String name;

	public Printer() {
		name = "";
	}
    public void setName(String name) {
        this.name = name;
    }

    public void print() {
        printString(this.name);
    }

    private void printString(String s) {
        System.out.println(s + " (" + s.length() + ")");
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
		//printer.setName("asdf");
        printer.print();
    }
}
