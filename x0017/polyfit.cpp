#include "polyfit.h"

std::vector<double> polyfit(const std::vector<double> &x, const std::vector<double> &y, int n);

void test_polyfit();

std::vector<double> polyfit(const std::vector<double> &x, const std::vector<double> &y, int n) {
    if (x.size() != y.size() || x.empty() || y.empty() || n <= 0 || x.size() <= n) {
        throw std::invalid_argument("invalid_argument");
    }
    std::cout.precision(4);                        //set precision
    std::cout.setf(std::ios::fixed);
    int N = x.size();
    //Array that will store the values of sigma(xi),sigma(xi^2),sigma(xi^3)....sigma(xi^2n)
    double X[2 * n + 1];
    for (int i = 0; i < 2 * n + 1; i++) {
        X[i] = 0;
        //consecutive positions of the array will store N,sigma(xi),sigma(xi^2),sigma(xi^3)....sigma(xi^2n)
        for (int j = 0; j < N; j++) {
            X[i] = X[i] + pow(x[j], i);
        }
    }
    //B is the Normal matrix(augmented) that will store the equations, 'a' is for value of the final coefficients
    double B[n + 1][n + 2], a[n + 1];

    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            //Build the Normal matrix by storing the corresponding coefficients at the right positions except the last column of the matrix
            B[i][j] = X[i + j];
        }
    }

    //Array to store the values of sigma(yi),sigma(xi*yi),sigma(xi^2*yi)...sigma(xi^n*yi)
    double Y[n + 1];
    for (int i = 0; i < n + 1; i++) {
        Y[i] = 0;
        for (int j = 0; j < N; j++) {
            //consecutive positions will store sigma(yi),sigma(xi*yi),sigma(xi^2*yi)...sigma(xi^n*yi)
            Y[i] = Y[i] + pow(x[j], i) * y[j];
        }
    }
    //load the values of Y as the last column of B(Normal Matrix but augmented)
    for (int i = 0; i <= n; i++) {
        B[i][n + 1] = Y[i];
    }
    //n is made n+1 because the Gaussian Elimination part below was for n equations, but here n is the degree of polynomial and for n degree we get n+1 equations
    n = n + 1;
    std::cout << "\nThe Normal(Augmented Matrix) is as follows:\n";
    //print the Normal-augmented matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= n; j++)
            std::cout << B[i][j] << std::setw(16);
        std::cout << "\n";
    }

    //From now Gaussian Elimination starts(can be ignored) to solve the set of linear equations (Pivotisation)
    for (int i = 0; i < n; i++) {
        for (int k = i + 1; k < n; k++) {
            if (B[i][i] < B[k][i]) {
                for (int j = 0; j <= n; j++) {
                    double temp = B[i][j];
                    B[i][j] = B[k][j];
                    B[k][j] = temp;
                }
            }
        }
    }

    //loop to perform the gauss elimination
    for (int i = 0; i < n - 1; i++) {
        for (int k = i + 1; k < n; k++) {
            double t = B[k][i] / B[i][i];
            for (int j = 0; j <= n; j++) {
                //make the elements below the pivot elements equal to zero or elimnate the variables
                B[k][j] = B[k][j] - t * B[i][j];
            }
        }
    }

    //back-substitution
    for (int i = n - 1; i >= 0; i--) {
        //x is an array whose values correspond to the values of x,y,z..
        a[i] = B[i][n];
        for (int j = 0; j < n; j++)
            //then subtract all the lhs values except the coefficient of the variable whose value is being calculated
            if (j != i)
                a[i] = a[i] - B[i][j] * a[j];
        a[i] = a[i] / B[i][i];//now finally divide the rhs by the coefficient of the variable to be calculated
    }
    std::cout << "\nThe values of the coefficients are as follows:\n";
    for (int i = n - 1; i >= 0; i--)
        std::cout << "x^" << i << "=" << a[i] << std::endl;            // Print the values of x^0,x^1,x^2,x^3,....
    std::cout << "\nHence the fitted Polynomial is given by:\ny=";
    std::vector<double> coefficients;
    for (int i = n - 1; i >= 0; i--) {
        std::cout << " + (" << a[i] << ")" << "x^" << i;
        coefficients.push_back(a[i]);
    }
    std::cout << "\n";
    return coefficients;
}


void test_polyfit() {
    /*
     * you can use matlab polyfit to test
     *  x = [1,2,3,4,5];
     *  y = [6,7,9,3,2];
     *  p = polyfit(x,y,3)  % 进行拟合
    * */
    std::vector<double> x{1, 2, 3, 4, 5};
    std::vector<double> y{6, 7, 9, 3, 2};

    /* y= + (0.3333)x^3 + (-3.8571)x^2 + (11.8095)x^1 + (-2.6000)x^0 */
    std::vector<double> coefficients = polyfit(x, y, 3);
    for (double coefficient : coefficients) {
        std::cout << coefficient << std::endl;
    }

    /*
     * you can use matlab polyfit to test
     *  x = [1,2,3,4,5];
     *  y = [6,7,9,3,2];
     *  p = polyfit(x,y,2)  % 进行拟合
    * */
    /* y= + (-0.8571)x^2 + (3.9429)x^1 + (3.0000)x^0 */
    coefficients = polyfit(x, y, 2);
    for (double coefficient : coefficients) {
        std::cout << coefficient << std::endl;
    }
}