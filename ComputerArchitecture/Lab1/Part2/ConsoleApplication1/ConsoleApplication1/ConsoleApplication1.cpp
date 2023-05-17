#include <iostream>
#include <mpi.h>

int main(int argc, char** argv)
{
    srand(time(NULL));
    int size, rank;

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);


    const int n = 5, m = 5;

    double first[n * m], tempFirst[n * m];
    double second[n * m], tempSecond[n * m];

    const int msize = n * m;


    int mas[] = { -1, 0, 1 };


    if (rank == 0)
    {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++)
            {
                first[i * n + j] = mas[0 + rand() % 2];
                second[i * n + j] = mas[0 + rand() % 2];
            }
        }
    }

    int* numberOfElements = new int[size];
    int* displs = new int[size];
    int del = 0;
    int ost = msize % size;
    int n1 = msize / size;

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

    double third[n * m], tempThird[n * m];

    MPI_Scatterv(&first[0], numberOfElements, displs, MPI_DOUBLE, &tempFirst[0], numberOfElements[rank], MPI_DOUBLE, 0, MPI_COMM_WORLD);
    MPI_Scatterv(&second[0], numberOfElements, displs, MPI_DOUBLE, &tempSecond[0], numberOfElements[rank], MPI_DOUBLE, 0, MPI_COMM_WORLD);

    for (int i = 0; i < numberOfElements[rank]; i++)
    {
        tempThird[i] = tempFirst[i] - tempSecond[i];
    }

    MPI_Gatherv(&tempThird, numberOfElements[rank], MPI_DOUBLE, &third, numberOfElements, displs, MPI_DOUBLE, 0, MPI_COMM_WORLD);

    if (rank == 0)
    {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++)
            {
                std::cout << first[i * n + j] << " ";
            }
            std::cout << std::endl;
        }
        std::cout << std::endl;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++)
            {
                std::cout << second[i * n + j] << " ";
            }
            std::cout << std::endl;
        }
        std::cout << std::endl;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++)
            {
                std::cout << third[i * n + j] << " ";
            }
            std::cout << std::endl;
        }
    }

    delete[] first;
    delete[] second;
    delete[] third;

    MPI_Finalize;

}