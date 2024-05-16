package com.mycom.springboot.common;

import java.util.ArrayList;
import java.util.Comparator;

import com.mycom.springboot.attraction.dto.SearchDto;

public class DistanceCalculator {
    public static void sortByDistance(ArrayList<SearchDto> list, double currentLat, double currentLon) {
        // Heap 정렬을 위한 Comparator 생성
        Comparator<SearchDto> comparator = new Comparator<SearchDto>() {
            @Override
            public int compare(SearchDto dto1, SearchDto dto2) {
                double distance1 = calculateDistance(currentLat, currentLon, dto1.getLatitude(), dto1.getLongitude());
                double distance2 = calculateDistance(currentLat, currentLon, dto2.getLatitude(), dto2.getLongitude());
                return Double.compare(distance1, distance2);
            }
        };

        // Heap 정렬 수행
        heapSort(list, comparator);
    }

    private static <T> void heapSort(ArrayList<T> list, Comparator<? super T> comparator) {
        int n = list.size();

        // Max heap 생성
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(list, n, i, comparator);

        // Heap에서 요소를 하나씩 추출하여 정렬
        for (int i = n - 1; i > 0; i--) {
            // 현재 루트 노드를 마지막 노드와 교환
            T temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            // 루트 노드에서 heapify 수행
            heapify(list, i, 0, comparator);
        }
    }

    private static <T> void heapify(ArrayList<T> list, int n, int i, Comparator<? super T> comparator) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 왼쪽 자식 노드와 비교
        if (left < n && comparator.compare(list.get(left), list.get(largest)) > 0)
            largest = left;

        // 오른쪽 자식 노드와 비교
        if (right < n && comparator.compare(list.get(right), list.get(largest)) > 0)
            largest = right;

        // 루트 노드가 최대값이 아닌 경우 교환 및 재귀 호출
        if (largest != i) {
            T swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);

            heapify(list, n, largest, comparator);
        }
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 두 지점 간의 거리 계산 (Haversine formula)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}