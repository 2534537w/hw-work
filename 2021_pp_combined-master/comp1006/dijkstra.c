/*
 *  dijkstra.c
 *  ProgrammingPortfolio
 *
 *  Created by Steven Bagley on 08/12/2021.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <float.h>
#include "graph.h"
#include "dijkstra.h"

static int max_elementId(int* ids, int len) {
    int res = ids[0], i = 0;
    for(i = 1; i < len; i++) {
        res = res > ids[i] ? res : ids[i];
    }
    return res;
}

static int edge_weigth_from_to(Graph* graph, int from, int to) {
    Edge* e;
    if(from == to)  return 0;
    e = get_edge(graph, from, to);
    return e ? e->weight : DBL_MAX;
}

/* find shortest paths between source node id and all other nodes in graph. */
/* upon success, returns an array containing a table of shortest paths.  */
/* return NULL if *graph is uninitialised or an error occurs. */
/* each entry of the table array should be a Path */
/* structure containing the path information for the shortest path between */
/* the source node and every node in the graph. If no path exists to a */
/* particular desination node, then next should be set to -1 and weight */
/* to DBL_MAX in the Path structure for this node */
Path *dijkstra(Graph *graph, int id, int *pnEntries) {
    Path *table = NULL;
    int* vertexs, *path, *shortest, *used;
    int from = id, to, vi;
    int ver_num, i = 0, k = 0;

    /* Insert your implementation of Dijkstra's algorithm here */
    if (graph == NULL || find_vertex(graph, id) == NULL)
        return NULL;
    vertexs = get_vertices(graph, &ver_num);
    *pnEntries = max_elementId(vertexs, ver_num) + 1;

    /* wechat is follow three lines*/
    if ((table = (Path*) malloc(sizeof(Path) * (*pnEntries)) ) == NULL) {
        fprintf(stderr, "error: unable to initialise path.\n");
        exit(EXIT_FAILURE);
    }
    /* 151 */
    if ((used = (int*) malloc(sizeof(int) * (*pnEntries))) == NULL) {
        fprintf(stderr, "error: unable to initialise used.\n");
        exit(EXIT_FAILURE);
    }
    /*  7240  */
    if ((path = (int*) malloc(sizeof(int) * (*pnEntries))) == NULL) {
        fprintf(stderr, "error: unable to initialise path.\n");
        exit(EXIT_FAILURE);
    }
    /* 6001 */
    if ((shortest = (int*) malloc(sizeof(int) * (*pnEntries))) == NULL) {
        fprintf(stderr, "error: unable to initialise shortest.\n");
        exit(EXIT_FAILURE);
    }

    /* init table */
    for(i = 0; i < ver_num; i++) {
        to = vertexs[i];
        table[to].weight = edge_weigth_from_to(graph, from, to);
        if(from != to && table[to].weight < DBL_MAX) path[to] = id;
        else path[to] = -1;
        table[to].next_hop = i;
        used[to] = 0;
    }
    used[from] = 1;

    while(1) {
        float min = DBL_MAX;
        int by = -1;
        for(i = 0; i < ver_num; i++) {
            to = vertexs[i];
            if(used[to] == 0) {
                if(table[to].weight < min) {
                    min = table[to].weight;
                    by = vertexs[i];
                }
            }		
        }
        if(by == -1) break;
        used[by] = 1;
        for(i = 0; i < ver_num; i++) {
            to = vertexs[i];
            vi = edge_weigth_from_to(graph, by, to);
            if(used[to] == 0 && 
                    vi != DBL_MAX &&
                    table[by].weight + vi < table[to].weight) {
                table[to].weight = vi + table[by].weight;
                path[to] = by;
            }
        }
    }	
    for(i = 0; i < ver_num; i++) {
        to = vertexs[i];
        /*printf("from %d  end %d, dist %f\n", from, to, table[to].weight);*/
        if(from == to) continue;
        k = 0;
        shortest[k] = to;
        while(path[shortest[k]] != from) {
            /*printf("shortest[%d] = %d\n", k, shortest[k]);*/
            k++;
            shortest[k] = path[shortest[k-1]];
        }
        k++;
        shortest[k] = from;
        /*
           for(j = k; j >= 0; j--)
           printf("%d -> ", shortest[j]);
           printf("\n");
         */
        table[to].next_hop = shortest[k-1]; 
    }

    table[from].weight = DBL_MAX;
    table[from].next_hop = -1;

    free(used);
    free(vertexs);
    free(path);
    free(shortest);
    return table;
}

