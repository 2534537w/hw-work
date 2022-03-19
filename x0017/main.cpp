#include "fir_filter.h"
#include "medflt.h"
#include "polyfit.h"
#include <iostream>

using namespace std;

/////////////////////// GNU plotting//////////////////////////////////////////////////////////
void plot(float* input, float* output, int n) {
    //Plotting with GNU Plot
    FILE *gnuplot;
    char gnuPlotCommandString[500]="";
    char title[200]="";
    char xLabel[200]="";
    char yLabel[200]="";

    //Select and Set the gnulot term for setting the terminal or plot size
    const int NUM_TERMS=5; //from 0 to 4
    
    //#define NUM_TERMS=5; //from 0 to 4
    char gnuplotTerms[NUM_TERMS][50]={"wxt","x11","qt","fail"};

    //select and get the term type, otherwise fail and out
    int termNo=0;
    for(termNo=0;termNo<NUM_TERMS;termNo++){
        sprintf(gnuPlotCommandString,"gnuplot -e \"set term %s\" 2>1",gnuplotTerms[termNo]);
        gnuplot=popen(gnuPlotCommandString,"r");
        char tempc;
        tempc=fgetc(gnuplot);
        if(feof(gnuplot)){
            break;
        }
        if(tempc) //do nothing. supressing the compiler warning for unused compiler
        fclose(gnuplot);
    }
    if(termNo==4){ //The term option is not available
        char strError[100];
        sprintf(strError,"Cannot find a suitable terminal type for GnuPlot, please ensure gnuplot-x11 is installed");
        printf("[ERROR]: %s\n", strError);
        exit(-1);
    }

    //Open the gnuplot for writing/plotting
    sprintf(gnuPlotCommandString, "gnuplot -persistent 2> /dev/null");
    if ( (gnuplot = popen(gnuPlotCommandString, "w")) == NULL){
        char strError[100];
        sprintf(strError,"Failed to open gnuplot executable");
        printf("[ERROR]: %s\n", strError);
        exit(-1);
    }

    //Set the plot title, xlabel and ylabel
    sprintf(title, "Originial and filtered samples");
    sprintf(xLabel, "Time");
    sprintf(yLabel, "Amplitude");

    fprintf(gnuplot, "set terminal %s size 500,400\n", gnuplotTerms[termNo]);
    fprintf(gnuplot, "set title '%s'\n", title);
    fprintf(gnuplot, "set xlabel '%s'\n", xLabel);
    fprintf(gnuplot, "set ylabel '%s'\n", yLabel);

    //Plot the data
    fprintf(gnuplot,"plot '-' w lines lc rgb 'blue' title \"sampled data\", '-' w lines lc rgb 'red' title \"Filtered data\" ");
    for(int i=0;i<n;i++){
        fprintf(gnuplot,"%d %lf\n",i,input[i]);
    }
    fprintf(gnuplot,"e\n");
    for(int i=0;i<n;i++){
        fprintf(gnuplot,"%d %lf\n",i,output[i]);
    }

    //Close the GNU plot piping
    fflush(gnuplot);
    fclose(gnuplot);
}


void generate_signal(float* sign, int n) {
    //Generate the input values
    const float pi=3.14159265358979;
    const float f1=10; //Hz
    const float f2=100;
    const float f3=1000;
    const float f4=10000;
    for(int i=0;i<n;i++){   
        sign[i] = (float)sin(2*pi*f1*i/n) + (float)sin(2*pi*f2*i/n) + (float)sin(2*pi*f3*i/n) + (float)sin(2*pi*f4*i/n); //one cycle t=i/n=0 to 1     
    }
}

int main(int argv, char* argc[]){   

    test_median_filter1D();
    test_polyfit();

    int n=1000;
    float input[n];
    float output[n];

    generate_signal(input, n);

    float fs = 26000;
    float flp = 200.0/fs;

    FIR_filter lp(n, flp, 0, fir_type_lowpass, hamming);

    for(int i = 0;  i< n; i++)
        output[i] = lp.filter(input[i]);

    plot(input, output, n);

    float fhp = 3000.0/fs;

    FIR_filter hp(n, fhp, 0, fir_type_highpass, hamming);

    for(int i = 0;  i< n; i++)
        output[i] = hp.filter(input[i]);

    plot(input, output, n);

    float fbpl = 200.0/fs;
    float fbph = 3000.0/fs;
    FIR_filter bp(n, fbpl, fbph, fir_type_highpass, hamming);

    for(int i = 0;  i< n; i++)
        output[i] = bp.filter(input[i]);

    plot(input, output, n);
    return 0;
}