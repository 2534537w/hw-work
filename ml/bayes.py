import pandas as pd
from scipy.io import loadmat
import numpy as np
from sklearn.model_selection import train_test_split
from matplotlib import pyplot as plt
import knn2


class NaiveBayes():
    def fit(self, X, y):
        self.classes = np.unique(y)
        self.parameters = {}
        for i, c in enumerate(self.classes):
            X_Index = X[np.where(y == c)]
            X_index_mean = np.mean(X_Index, axis=0, keepdims=True)
            X_index_var = np.var(X_Index, axis=0, keepdims=True)
            parameters = {"mean": X_index_mean, "var": X_index_var, "prior": X_Index.shape[0] / X.shape[0]}
            self.parameters["class" + str(c)] = parameters

    def _pdf(self, X, classes):

        eps = 1e-4
        mean = self.parameters["class" + str(classes)]["mean"]
        var = self.parameters["class" + str(classes)]["var"]

        nume = np.exp(-(X - mean) ** 2 / (var * 2 + eps))
        deno = np.sqrt(np.pi * var * 2 + eps)

        result = np.sum(np.log(nume / deno), axis=1, keepdims=True)

        return result.T

    def _predict(self, X):

        output = []
        for y in range(self.classes.shape[0]):
            prior = np.log(self.parameters["class" + str(y)]["prior"])
            posterior = self._pdf(X, y)
            prediction = prior + posterior
            output.append(prediction)
        return output

    def predict(self, X):

        output = self._predict(X)
        output = np.reshape(output, (self.classes.shape[0], X.shape[0]))
        prediction = np.argmax(output, axis=0)
        return prediction


def nb():
    clf = NaiveBayes()
    clf.fit(X_train, y_train)
    y_pred = clf.predict(X_test)
    return np.mean(y_pred == y_test)


if __name__ == '__main__':
    ###########
    digit = loadmat('digits.mat')
    X = digit['X'].astype('float')
    y = digit['Y'][:, 0]
    np.seterr(divide='ignore')
    test_size_list = [0.6, 0.7, 0.8, 0.9]

    columns = ["knn_L2"] + ["NB"]
    accuracy_df = pd.DataFrame(
        data=np.zeros(len(test_size_list) * len(columns)).reshape(len(test_size_list), len(columns)),
        columns=columns)

    for i in range(len(test_size_list)):
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=test_size_list[i], random_state=2003)
        knn = accuracy_df.loc[i, "knn_L2"] = knn2.knn(X_test, y_test, X_train, y_train, "L2")
        t = accuracy_df.loc[i, "NB"] = nb()
        print(knn, t)
    accuracy_df

    train_size = [0.9, 0.8, 0.7, 0.6]
    plt.plot(train_size, accuracy_df.iloc[:, 0], '-', label="KNN")
    plt.plot(train_size, accuracy_df.iloc[:, 1], '-', label="Bayes")
    plt.xlabel('Train data size proportion')
    plt.ylabel('Accuracy')
    plt.title('Accuracy of Bayes vs KNN')
    plt.grid(True)
    plt.legend()
    plt.show()
