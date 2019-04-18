 //stage1 
        for (int i=1;i<101;i++){
            if (i%3 == 0 && i%5 != 0){
                System.out.println("Fizz");
            }else  if(i%3 != 0 && i%5 == 0){
                System.out.println("Buzz");
            }else if (i%3 == 0 && i%5 == 0){
                System.out.println("FizzBuzz");
            }else{
                System.out.println(i);
            }
        }

        //stage2-1
        for (int i=1;i<101;i++){
            if(i%3 == 0 && (i-3)%10 == 0){
                System.out.println(i);
            }
        }
        //stage2-2
        for (int i=1;i<101;i++){
            if(i%5 == 0 && i%3 != 0 && (i-5)%10 ==0){
                System.out.println(i);
            }
        }
        //stage2-3
        for (int i=1;i<101;i++){
            if (i%3 == 0 && i%5 == 0){
                System.out.println(i);
            }
        }
