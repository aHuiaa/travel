package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteService {
    Favorite findFavorite(String rid, int uid);

    void favorite(String rid, String uid);

    void noFavorite(String rid, String uid);
}
