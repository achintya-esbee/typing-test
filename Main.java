import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalTime;
import java.util.Vector;

public class Main {

    static String psg1 = "Medical transcription, also known as MT, is an allied health profession dealing with the process of transcribing voice-recorded medical reports that are dictated by physicians, nurses and other healthcare practitioners. Medical reports can be voice files, notes taken during a lecture, or other spoken material. These are dictated over the phone or uploaded digitally via the Internet or through smart phone apps.";

    static String psg2 = "Proofreader applicants are tested primarily on their spelling, speed, and skill in finding errors in the sample text. Toward that end, they may be given a list of ten or twenty classically difficult words and a proofreading test, both tightly timed. The proofreading test will often have a maximum number of errors per quantity of text and a minimum amount of time to find them. The goal of this approach is to identify those with the best skill set.";

    static String psg3 = "Business casual is an ambiguously defined dress code that has been adopted by many professional and white-collar workplaces in Western countries. It entails neat yet casual attire and is generally more casual than informal attire but more formal than casual or smart casual attire. Casual Fridays preceded widespread acceptance of business casual attire in many offices.";

    static String psg4 = "A teacher's professional duties may extend beyond formal teaching. Outside of the classroom teachers may accompany students on field trips, supervise study halls, help with the organization of school functions, and serve as supervisors for extracurricular activities. In some education systems, teachers may have responsibility for student discipline.";

    static String psg5 = "This is a test string, not to be added in the main code.";

    static String para1[] = psg1.split(" ");
    static String para2[] = psg2.split(" ");
    static String para3[] = psg3.split(" ");
    static String para4[] = psg4.split(" ");
    static String para5[] = psg5.split(" ");

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        Vector <String> testpsg = new Vector <String>();

        System.out.println("Choose a passage to type");
        System.out.println("Make a choice: [1/2/3/4]");
        System.out.println();

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                for (int i = 0; i < para1.length; i++) {
                    System.out.print(para1[i] + " ");
                    testpsg.add(para1[i]);
                }
                break;
            case 2:
                for (int i = 0; i < para2.length; i++) {
                    System.out.print(para2[i] + " ");
                    testpsg.add(para2[i]);
                }
                break;
            case 3:
                for (int i = 0; i < para3.length; i++) {
                    System.out.print(para3[i] + " ");
                    testpsg.add(para3[i]);
                }
                break;
            case 4:
                for (int i = 0; i < para4.length; i++) {
                    System.out.print(para4[i] + " ");
                    testpsg.add(para4[i]);
                }
                break;
            case 5:
                for (int i = 0; i < para5.length; i++) {
                    System.out.print(para5[i] + " ");
                    testpsg.add(para5[i]);
                }
                break;
            default:
                System.out.println("Invalid Choice");
                System.exit(0);
        }

        System.out.println("\n\n");

        System.out.println("Start typing after you see [START] in:");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("[START]\n");

        double start = LocalTime.now().toNanoOfDay();
        scan.nextLine();
        String typedWords = scan.nextLine();
        String typed[] = typedWords.split(" ");
        Vector <String> typedpsg = new Vector <String>(Arrays.asList(typed));

        boolean ws_flag = true;
        while (ws_flag) {
            if (typedpsg.get(0) == "") {
                typedpsg.remove(0);
                continue;
            }
            ws_flag = false;
        }

        double end = LocalTime.now().toNanoOfDay();
        int errors = accuracyCheck(testpsg, typedpsg);
        float accuracy = ((float) (testpsg.size() - errors) / testpsg.size()) * 100;
        double timeElapsed = end - start;
        double seconds = timeElapsed / 1000000000.0;
        int numChars = typedWords.length();
        int wpm = (int) ((((double) numChars / 6) / seconds) * 60);

        System.out.println("Your typing speed is " + wpm + " wpm!");
        System.out.println("Your accuracy is " + accuracy + " %!");
        scan.close();
    }

    static int accuracyCheck(Vector<String> test, Vector<String> typed) {
        int limit = 0, test_k = 0, error = 0, typed_k = 0;

        if (test.size() >= typed.size()) {
            limit = typed.size();
            error = test.size() - typed.size();
        } else
            limit = test.size();
        //System.out.println(limit);
        while (test_k != limit) {
            if (test.get(test_k).equals(typed.get(typed_k))) {
                test_k++;
                typed_k++;
                continue;
            } else {
                if (test_k < limit-1) {
                    if (test.get(test_k + 1).equals(typed.get(typed_k + 1))) {
                        error++;
                        test_k++;
                        typed_k++;
                        continue;
                    } else {
                        if (test_k > 0) {
                            if (test.get(test_k - 1).equals(typed.get(typed_k - 1))) {
                                error += 1;
                                test_k += 2;
                                typed_k++;
                            }
                        } else {
                            if (test.get(test_k + 2).equals(typed.get(typed_k + 1))) {
                                error += 1;
                                test_k += 2;
                                typed_k++;
                            }
                        }
                    }
                } else {
                    error++;
                    test_k++;
                }
            }
        }
        return error;
    }
}
