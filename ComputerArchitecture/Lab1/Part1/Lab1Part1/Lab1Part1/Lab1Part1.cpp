#include <iostream>
#include <vector>
#include "mpi.h"


int main(int argc, char** argv)
{
    srand(time(NULL));
    int size, rank;

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    const int l = 10;
    int n1 = l / size;

    double first[l], tempFirst[l];
    double second[l], tempSecond[l];

    if (rank == 0)
    {
        for (int i = 0; i < l; i++)
        {
            first[i] = 0 + (double)rand() / RAND_MAX;
            second[i] = 0 + (double)rand() / RAND_MAX;
        }
    }


    int* numberOfElements = new int[size];
    int* displs = new int[size];
    int del = 0;
    int ost = l % size;

    for (int i = 0; i < size; i++)
    {
        displs[i] = del;
        numberOfElements[i] = n1;
        del += n1;
        if (ost > 0) {
            ++numberOfElements[i];
            ++del;
            --ost;
        }
    }

    double res = 0;

    MPI_Scatterv(&first[0], numberOfElements, displs, MPI_DOUBLE, &tempFirst[0], numberOfElements[rank], MPI_DOUBLE, 0, MPI_COMM_WORLD);
    MPI_Scatterv(&second[0], numberOfElements, displs, MPI_DOUBLE, &tempSecond[0], numberOfElements[rank], MPI_DOUBLE, 0, MPI_COMM_WORLD);
    
    double tempres = 0;

    for (int i = 0; i < numberOfElements[rank]; i++)
    {
        tempres += (tempFirst[i] * tempSecond[i]);
    }
    
    MPI_Reduce(&tempres, &res, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

    if (rank == 0) {

        for (int i = 0; i < l; i++) {
            std::cout << "i = " << i << " ; first[i] = " << first[i] << " ; second[i] = " << second[i] << std::endl;
        }

        std::cout << "\n";
       
        std::cout << "Final result = " << res;
    }

    delete[] first;
    delete[] second;

    MPI_Finalize();
    return 0;
}