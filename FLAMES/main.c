#include <stdio.h>
#include <string.h>
int Calc(char name1[], char name2[]) {
    char f[100];
    int i, j, c = 0;

    // Loop through name1 and name2 to find differing characters
    for (i = 0; name1[i] != '\0'; i++) {
        int found = 0; // Flag to check if character in name1 is found in name2
        for (j = 0; name2[j] != '\0'; j++) {
            if (name1[i] == name2[j]) {
                found = 1; // Character found in name2
                break;
            }
        }
        if (!found) {
            f[c] = name1[i];
            c++;
        }
    }

    // Loop through name2 to find differing characters
    for (i = 0; name2[i] != '\0'; i++) {
        int found = 0; // Flag to check if character in name2 is found in name1
        for (j = 0; name1[j] != '\0'; j++) {
            if (name2[i] == name1[j]) {
                found = 1; // Character found in name1
                break;
            }
        }
        if (!found) {
            f[c] = name2[i];
            c++;
        }
    }
    f[c] = '\0'; // Null terminate the resulting string
    //printf("%s", f);
    return strlen(f);
}
void Flames(int n,char f[])
{
    int i,l;
    l=strlen(f);
    n=n-1;
    while(l>1)//Repeat the process till a  single letter is reached
    {
        i=n;
        while(i>=l)// if the iteration value is greater than the current length iterate from beginning of the word
            {
            i=i-l;
        }
        for (; i < l- 1; i++)
         {
        f[i] = f[i + 1];// remove the particular letter
        }
    f[l- 1] = '\0';//terminate a string
    l=strlen(f);
}
    printf("%s\n",f);
    if(!(strcmp("F",f)))
        printf("FRIENDS! xD");
    else if (!(strcmp("L",f)))
        printf("LOVE!!!\n");
    else if(!strcmp("A",f))
        printf("AFFECTION");
    else if(!strcmp("M",f))
        printf("MARRIAGE!! xD");
    else if(!(strcmp("E",f)))
        printf("ENEMIES",f);
    else if(!(strcmp("S",f)))
        printf("SISTER");
    }
int main()
{
    char it[]="FLAMES",name1[100],name2[100];
    int totLet,j;
    printf("Enter your name");
    scanf("%[^\n]%*c",name1);
    strcpy(name1,strlwr(name1));
    printf("Enter 2nd name");
    scanf("%[^\n]%*c",name2);
    strcpy(name2,strlwr(name2));
    printf("%s\n%s\n",name1,name2);
    totLet=Calc(name1,name2);
    //printf("%d\n",totLet);
    Flames(totLet,it);
    return 0;
}
