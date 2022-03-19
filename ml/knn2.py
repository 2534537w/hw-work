import datetime
import operator

import pandas as pd
from matplotlib import pyplot as plt
from scipy.io import loadmat
import numpy as np
from sklearn.model_selection import train_test_split


def dist_l1(diff_mat):
    sqDiffMat = abs(diff_mat)
    sqDistances = sqDiffMat.sum(axis=1)
    distances = sqDistances
    return distances


def dist_l2(diff_mat):
    sqDiffMat = diff_mat ** 2
    sqDistances = sqDiffMat.sum(axis=1)

    distances = sqDistances ** 0.5
    return distances


def dist_inf(diff_mat):
    sqDiffMat = abs(diff_mat)
    sqDistances = sqDiffMat.max(axis=1)
    distances = sqDistances
    return distances


def classify0(testData, trainDataSet, trainLabelSet, k, distType):
    dataSetSize = trainDataSet.shape[0]

    diffMat = np.tile(testData, (dataSetSize, 1)) - trainDataSet

    if distType == "L1":
        distances = dist_l1(diffMat)
    elif distType == "L2":
        distances = dist_l2(diffMat)
    elif distType == "INF":
        distances = dist_inf(diffMat)
    else:
        distances = dist_l2(diffMat)

    sortedDistIndicies = distances.argsort()
    classCount = {}

    for i in range(k):
        voteIlabel = trainLabelSet[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel, 0) + 1

    sortedClassCount = sorted(
        classCount.items(), key=operator.itemgetter(1), reverse=True)

    return sortedClassCount[0][0]


def classify1(testData, trainDataSet, trainLabelSet, k, distType):
    preds = []
    for data in testData:
        preds.append(classify0(data, trainDataSet, trainLabelSet, k, distType))
    return preds


def knn(testData, testLabel, trainDataSet, trainLabelSet, distType):
    k_list = range(2, 3)
    knn_accuracy_list_k = []
    for k in k_list:
        label_pred = classify1(testData, trainDataSet, trainLabelSet, k, distType)
        knn_accuracy_list_k.append(np.mean(label_pred == testLabel))
    return max(knn_accuracy_list_k)


def main():
    digit = loadmat('digits.mat')
    data = digit['X'].astype('float')
    label = digit['Y'][:, 0]

    test_size_list = [0.1, 0.2, 0.3, 0.4]
    columns = ['knn_L' + str(j) for j in range(1, 3)] + ["knn_INF"]
    accuracy_df = pd.DataFrame(data=np.zeros(3 * len(test_size_list)).reshape(len(test_size_list), 3), columns=columns)

    for i in range(len(test_size_list)):
        test_size = test_size_list[i]
        data_train, data_test, label_train, label_test = train_test_split(data, label, test_size=test_size,
                                                                          random_state=2003)
        for distType in ["L1", "L2", "INF"]:
            t = knn(data_test, label_test, data_train, label_train, distType)
            print(test_size, distType, t)
            accuracy_df.loc[i, 'knn_' + distType] = t

    train_size = test_size_list[::-1]
    plt.plot(train_size, accuracy_df.iloc[:, 0], '-', label="L1")
    plt.plot(train_size, accuracy_df.iloc[:, 1], '-', label="L2")
    plt.plot(train_size, accuracy_df.iloc[:, 2], '-', label="L_inf")
    plt.xlabel('Train data size proportion')
    plt.ylabel('Accuracy')
    plt.title('Accuracy of knn in L1, L2, and L_inf')
    plt.grid(True)
    plt.legend()
    plt.show()


if __name__ == "__main__":
    t1 = datetime.datetime.now()
    main()
    t2 = datetime.datetime.now()
    print((t2 - t1).seconds, " s")
