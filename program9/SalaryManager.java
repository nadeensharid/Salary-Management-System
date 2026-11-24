package program9;

import java.io.*;
import java.util.*;

public class SalaryManager implements Raiseable {

    public void create(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not create file.");
        }
    }

    public void display(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));

            System.out.printf("%-10s %-12s %-10s%n", "ID", "Salary", "Years");
            System.out.println("--------------------------------");

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(":");

                int id = Integer.parseInt(parts[0]);
                double salary = Double.parseDouble(parts[1]);
                int years = Integer.parseInt(parts[2]);

                System.out.printf("%-10d $%-11.2f %-10d%n", id, salary, years);
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("Bad data in file.");
        }
    }

    public boolean addTo(String inFileName, String outFileName, int id, double salary, int yearsOfService) {
        boolean added = false;

        try {
            Scanner in = new Scanner(new File(inFileName));
            PrintWriter out = new PrintWriter(outFileName);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(":");

                int currentId = Integer.parseInt(parts[0]);

                if (currentId == id) {
                    out.println(line);

                    while (in.hasNextLine()) {
                        out.println(in.nextLine());
                    }

                    in.close();
                    out.close();
                    return false;
                }

                if (!added && id < currentId) {
                    out.println(id + ":" + salary + ":" + yearsOfService);
                    added = true;
                }

                out.println(line);
            }

            if (!added) {
                out.println(id + ":" + salary + ":" + yearsOfService);
                added = true;
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error.");
        } catch (Exception e) {
            System.out.println("Bad data.");
        }

        return added;
    }

    public boolean removeFrom(String inFileName, String outFileName, int id) {
        boolean removed = false;

        try {
            Scanner in = new Scanner(new File(inFileName));
            PrintWriter out = new PrintWriter(outFileName);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(":");

                int currentId = Integer.parseInt(parts[0]);

                if (currentId == id) {
                    removed = true;
                } else {
                    out.println(line);
                }
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error.");
        } catch (Exception e) {
            System.out.println("Bad data.");
        }

        return removed;
    }

    public void addService(String inFileName, String outFileName) {
        try {
            Scanner in = new Scanner(new File(inFileName));
            PrintWriter out = new PrintWriter(outFileName);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(":");

                int id = Integer.parseInt(parts[0]);
                double salary = Double.parseDouble(parts[1]);
                int years = Integer.parseInt(parts[2]);

                years++;

                out.println(id + ":" + salary + ":" + years);
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error.");
        } catch (Exception e) {
            System.out.println("Bad data.");
        }
    }

    public int raise(String inFileName, String outFileName, int yearsOfService, double salaryIncPercent) {
        int count = 0;

        try {
            Scanner in = new Scanner(new File(inFileName));
            PrintWriter out = new PrintWriter(outFileName);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parts = line.split(":");

                int id = Integer.parseInt(parts[0]);
                double salary = Double.parseDouble(parts[1]);
                int years = Integer.parseInt(parts[2]);

                if (years >= yearsOfService) {
                    salary = salary + (salary * salaryIncPercent / 100.0);
                    count++;
                }

                out.println(id + ":" + salary + ":" + years);
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error.");
        } catch (Exception e) {
            System.out.println("Bad data.");
        }

        return count;
    }

    public void mergeFiles(String inFileName1, String inFileName2, String outFileName) {
        try {
            Scanner file1 = new Scanner(new File(inFileName1));
            Scanner file2 = new Scanner(new File(inFileName2));
            PrintWriter out = new PrintWriter(outFileName);

            String line1 = null;
            String line2 = null;

            if (file1.hasNextLine()) {
                line1 = file1.nextLine();
            }

            if (file2.hasNextLine()) {
                line2 = file2.nextLine();
            }

            while (line1 != null && line2 != null) {
                String[] parts1 = line1.split(":");
                String[] parts2 = line2.split(":");

                int id1 = Integer.parseInt(parts1[0]);
                int id2 = Integer.parseInt(parts2[0]);

                double salary1 = Double.parseDouble(parts1[1]);
                double salary2 = Double.parseDouble(parts2[1]);

                if (id1 < id2) {
                    out.println(line1);

                    if (file1.hasNextLine()) {
                        line1 = file1.nextLine();
                    } else {
                        line1 = null;
                    }

                } else if (id2 < id1) {
                    out.println(line2);

                    if (file2.hasNextLine()) {
                        line2 = file2.nextLine();
                    } else {
                        line2 = null;
                    }

                } else {
                    if (salary1 >= salary2) {
                        out.println(line1);
                    } else {
                        out.println(line2);
                    }

                    if (file1.hasNextLine()) {
                        line1 = file1.nextLine();
                    } else {
                        line1 = null;
                    }

                    if (file2.hasNextLine()) {
                        line2 = file2.nextLine();
                    } else {
                        line2 = null;
                    }
                }
            }

            while (line1 != null) {
                out.println(line1);

                if (file1.hasNextLine()) {
                    line1 = file1.nextLine();
                } else {
                    line1 = null;
                }
            }

            while (line2 != null) {
                out.println(line2);

                if (file2.hasNextLine()) {
                    line2 = file2.nextLine();
                } else {
                    line2 = null;
                }
            }

            file1.close();
            file2.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File error.");
        } catch (Exception e) {
            System.out.println("Bad data.");
        }
    }
}