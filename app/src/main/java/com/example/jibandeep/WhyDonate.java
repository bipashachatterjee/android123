package com.example.jibandeep;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WhyDonate extends AppCompatActivity {
Button s,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_donate);

s=findViewById(R.id.button);
b=findViewById(R.id.button5);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String body ="WHY SHOULD I DONATE BLOOD?\n" +
                        "\n" +
                        "Donating blood regularly has proved beneficial not only for the recipient but also the donor. It is helpful for the vital organs, and it reduces risk for chronic diseases such as cancer and stroke.\n" +
                        "\n" +
                        "For certain treatments like chemotherapy, blood is required on a daily basis, whereas victims of accident may require up to 100 units of blood transfusion. In India, blood shortage is more than 2 million pint and counting.\n" +
                        "\n" +
                        "Here are 8 reasons why you should donate blood :\n" +
                        "\n" +
                        "1. Reduce risk of heart attacks and liver ailment :\n" +
                        "\n" +
                        "Donating blood regularly is beneficial to prevent and reduce heart attacks and liver ailment. The risk of heart and liver related problem is caused by the iron overload in the body. Donating blood helps in maintaining the iron level in the body and thus reduce those risk.\n" +
                        "\n" +
                        "2. Lower the risk of cancer :\n" +
                        "\n" +
                        "Cancer is the most feared and deadly disease. Blood donation helps in lowering the risk of cancer. By donating blood regularly the iron level in the blood is balanced and the risk of cancer-related to the liver, lungs, and intestine gets lower.\n" +
                        "\n" +
                        "3. New blood cells :\n" +
                        "\n" +
                        "Once we donate blood, the body tries to restore the blood loss. This helps in the production of the new blood cells and maintain good health.\n" +
                        "\n" +
                        "4. Reduced risk of hemochromatosis :\n" +
                        "\n" +
                        "Hemochromatosis is a disease that occurs due to increase in the absorption of iron by the body. Blood donation helps in reducing iron overload in the body and prevent Hemochromatosis.\n" +
                        "\n" +
                        "5. Maintain Weight :\n" +
                        "\n" +
                        "It is recommended to donate blood for those who are overweight. Regularly donating blood helps in weight loss and burn fat up to 650 calories.\n" +
                        "\n" +
                        "6. Helps prevent premature ageing :\n" +
                        "\n" +
                        "While donating blood, you not only lose weight but it also helps in reducing stress. Stress is one of the reasons that triggers premature ageing. Blood donation helps in reducing stress on your mind and body. Also, keeps the skin tight and wrinkle-free.\n" +
                        "\n" +
                        " 7. Speeds up healing process :\n" +
                        "\n" +
                        "The body tries to adjust to the loss of red blood the cells we donate blood, these adjustments also help in accelerating the wound healing process.\n" +
                        "\n" +
                        "8. Lower cholesterol level :\n" +
                        "\n" +
                        "Blood contains iron, if the iron in blood is overloaded it can increase the chances of blockage in blood vessels. Blood donation can help to reduce the amount of iron in the blood thus helps in lower cholesterol.\n" +
                        "\n" +
                        "WHY SHOULD I DONATE ORGAN?\n" +
                        "\n" +
                        "The need for organ donors has been rising significantly over the years. This growing need is due to the fact that the number of people with end-stage organ failure has been increasing and, with advances in transplantation, a greater proportion of these people are eligible for organ transplantation.\n" +
                        "One of the biggest obstacles to organ transplantation is getting individuals to register to become organ donors before they are faced with a tragic situation. Consider these reasons why you should be an organ donor.\n" +
                        "\n" +
                        "Here are 8 reasons why you should donate organ :\n" +
                        "\n" +
                        "1. Organ donation is an opportunity to help others :\n" +
                        "\n" +
                        " People who are on an organ waiting list typically have end-stage organ disease that significantly impacts their quality of life and may be near the end of their life. Receiving an organ can become a life-changing event for these people. It can also help a family work through the grieving process and deal with their loss by knowing their loved one is helping save the lives of others.\n" +
                        "\n" +
                        "2. One organ donor can help multiple people :\n" +
                        "\n" +
                        "One organ donor has the potential to save eight lives, save or improve as many as 60 lives and enhance the eyesight of two.\n" +
                        "\n" +
                        "3. Living donors fill a crucial need :\n" +
                        "\n" +
                        " A living donor can donate a kidney or a portion of their liver to a friend or family member or even altruistically and continue to live a normal life with very little restrictions. People waiting for a kidney transplant make up more than 80 percent of people on the organ waiting list and people waiting for a liver transplant makeup approximately 12 percent.\n" +
                        "\n" +
                        "4. Nebraska needs more organ donors :\n" +
                        "\n" +
                        " In Nebraska alone, there are approximately 500 people waiting for an organ to become available. But only about 48 percent of the population of Nebraska has registered to become a donor.\n" +
                        "\n" +
                        "5. People are dying while waiting for an organ :\n" +
                        "\n" +
                        " Approximately 20 people die each day while they are waiting for an organ match to undergo a transplant.\n" +
                        "\n" +
                        "6. Organ donation can be a rewarding and positive experience :\n" +
                        "\n" +
                        "It can help a family work through the grieving process and deal with their loss by knowing their loved one is helping save the lives of others.\n" +
                        "\n" +
                        "7. There are no age exclusions to donate :\n" +
                        "\n" +
                        "If you are otherwise healthy, many of your organs could still be viable for an organ donation. The transplant surgeon evaluates the organs and decides whether they are suitable on a case-by-case basis.\n" +
                        "\n" +
                        "8. Very few medical conditions disqualify you from donating your organs :\n" +
                        "\n" +
                        " It may be determined that certain organs are not suitable for transplantation, but other tissues and organs may be fine. Simply put, a disease in one organ does not preclude other organs from being donated.\n" +
                        "\n"+
                        "                           Thank you.";
                String sub="WHY WE DONATE";
                myintent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myintent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myintent,"Share Using"));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WhyDonate.this, Mainmenu.class);
                startActivity(i);
            }
        });
    }
}