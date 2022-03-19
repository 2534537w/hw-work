//
// Created by 孟祥鹏 on 2022/3/19.
//

#include "medflt.h"


static inline double find_median(std::vector<double> &&back) {
    int m = back.size();
    std::sort(back.begin(), back.end());
    if (m % 2 == 1)
        return back[m / 2];

    return double(back[m / 2 - 1] + back[m / 2]) / 2.0;
}

std::vector<double> median_filter1D(const std::vector<double> &src, int windows_size) {
    if (src.empty() || windows_size <= 2)
        throw std::invalid_argument("invalid_argument");

    int size = src.size();
    if (windows_size >= size || size == 0)
        return src;

    std::vector<double> dst(src.size(), 0);
    int half = windows_size / 2;
    bool even = (windows_size % 2 == 0);

    int i = 0;
    for (i = 0; i < half; i++)
        dst[i] = src[i];

    for (i = half; i < size - half + even; i++) {
        auto start = src.begin() + i - half;
        auto end = start + windows_size;
        dst[i] = find_median(std::vector<double>(start, end));
    }
    for (i = size - half + even; i < size; i++)
        dst[i] = src[i];
    return dst;
}


void test_median_filter1D() {
    std::vector<double> arr{3, 1, 2, 5, 4, 8, 5, 0, 2, 3, 1};
    std::vector<double> dst3 = median_filter1D(arr, 3);
    if (dst3 != std::vector<double>{3, 2, 2, 4, 5, 5, 5, 2, 2, 2, 1,}) {
        std::cout << "error test_median_filter1D" << std::endl;
    }
    std::vector<double> dst4 = median_filter1D(arr, 4);
    if (dst4 != std::vector<double>{3, 1, 2.5, 3, 4.5, 5, 4.5, 3.5, 2.5, 1.5, 1,}) {
        std::cout << "error test_median_filter1D" << std::endl;
    }

    std::cout << " test_median_filter1D PASS " << std::endl;
}