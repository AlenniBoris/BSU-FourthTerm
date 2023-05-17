// Integral.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <omp.h>
#include<cmath>
#include<string>
#include<vector>
#include<stdio.h>

const double a = 0;
const double b = 5;
const double PI = 3.14159265358979323846;
double N, N2, h, h2, result, result2;

double functionF(int k){
    double x = a + h * k;
    if (x >= 0 && x < PI / 2) {
        return 1 - std::cos(x);
    }
    else{
        return x - (PI / 2);
    }
}
double checkF(int k) {
    double x = a + h * k;
    if (x >= 0 && x <= PI / 2) {
        return x - std::sin(x);
    }
    else{
        return std::pow(x, 2)/2 - x * (PI / 2);
    }
}


double functionG(int k){
    double x = a + h * k;
    if (x >= 0 && x < PI / 2) {
        return 1 - std::cos(x);
    }
    else {
        return x - (PI / 2);
    }
}
double checkG(int k) {
    double x = a + h2 * k;
    if (x >= 0 && x < PI / 2) {
        return x - std::sin(x);
    }
    else {
        return std::pow(x, 2)/2 - x * (PI / 2);
    }
}

int main()
{
    std::cin >> N;
    N2 = 4 * N;
    h = (b - a) / N;
    h2 = (b - a) / N2;
    double f;
    double g;
#pragma omp parellel
    {
#pragma omp for
        {
            result = 0;
            for (size_t i = 0; i < N + 1; i++)
            {
                if (i == 0 || i == N)
                {
                    result += functionF(i);
                }
                else {
                    if (i % 2 == 0)
                    {
                        result += 2 * functionF(i);
                    }
                    else
                    {
                        result += 4 * functionF(i);
                    }
                }
            }
            result *= h / 3;
            double value = checkF(b) - checkF(a);
            std::cout << " function = " << result << " " << "value = " << value << " " << " differrence = " << result - value << std::endl;
            std::cout << std::endl;
        }
    }
#pragma omp for
    {
        for (size_t i = 0; i < N2 + 1; i++)
        {
            if (i == 0 || i == N2)
            {
                result2 += functionG(i);
            }
            else {
                if (i % 2 == 0)
                {
                    result2 += 2 * functionG(i);
                }
                else
                {
                    result2 += 4 * functionG(i);
                }
            }
        }
        result2 *= h2 / 3;
        double res = checkG(b) - checkG(a);
        std::cout << " function = " << result2 << " " << "value = " << res << " " << " differrence = " << result2 - res << std::endl;
    }
}