// Lab3.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#include<cmath>
#include<string>
#include<vector>
#include<stdio.h>

const double a = -1;
const double b = 3;
double N, h, result;

double result1(int k) {
    double x = a + h * k;
    return std::pow(x, 3);
}

int main(int argc, char** argv)
{
    std::cin >> N;
    h = (b - a) / N;

#pragma omp parallel section
    {
#pragma omp for
        {
            for (int i = 0; i < N + 1; i++)
            {
                result = (result1(i + 1) - result1(i)) / h;
                double value = 3*std::pow(a + h * i, 2);
                std::cout << i << ":" << " function = " << result << " " << "value = " << value << " " << " differrence = " << result - value << std::endl;
            }
        }
    }

}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
