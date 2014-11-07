#ifndef _GAME_OBJECT_HPP_
#define _GAME_OBJECT_HPP

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include "Point.hpp"
#include <string>
using std::string;

//TODO: Enumerate GameObjectErrors

class GameObjectError{};

class GameObject{
    Point position_;
    Point size_;
    SDL_Texture* texture_;
    double scale_;
    Point rotation_;
    string filepath_;
public:
//protected: //for global renderer to work with his friend classes
    void draw(SDL_Renderer* renderer){
        if(renderer == (SDL_Renderer*)nullptr)
            throw GameObjectError();
        SDL_Rect rect;
        rect.x = position_.x();
        rect.y = position_.y();
        rect.w = size_.x();
        rect.h = size_.y();
        if(texture_ == (SDL_Texture*)nullptr)
            throw GameObjectError();
        SDL_RenderCopy(renderer, texture_, (SDL_Rect*)nullptr, &rect);
    }
public:
    GameObject(int x, int y, string filepath): position_(x, y), filepath_(filepath.c_str()){}
    GameObject(int x, int y){}
    
    Point get_position() const {return position_;}
    int get_x() const {return position_.x();}
    int get_y() const {return position_.y();}
    void set_x(int x) {position_.set_x(x);}
    void set_y(int y) {position_.set_y(y);}
    
    Point get_size() const {return size_;}
    int get_width() const {return size_.x();}
    int get_height() const {return size_.y();}
    void set_width(int w){size_.set_x(w);}
    void set_height(int h){size_.set_y(h);}
    
    double get_scale() const {return scale_;}
    void set_scale(double s){scale_ = s;}
    
    Point get_rotation() const {return rotation_;}
    int get_rotation_x() const {return rotation_.x();}
    int get_rotation_y() const {return rotation_.y();}
    
    void set_path(string path){filepath_ = path;}
    void load_texture(SDL_Renderer* renderer, string path){
        if(renderer == (SDL_Renderer*)nullptr)
            throw GameObjectError();
        SDL_Surface* surface = IMG_Load(path.c_str());
        if(surface == (SDL_Surface*)nullptr)
            throw GameObjectError();
        filepath_ = path;
        SDL_Texture* texture = SDL_CreateTextureFromSurface(renderer, surface);
        SDL_FreeSurface(surface);
        if(texture == (SDL_Texture*)nullptr)
            throw GameObjectError();
        if(texture_ != (SDL_Texture*)nullptr){
            SDL_DestroyTexture(texture_);
        }
        texture_ = texture;
    }
};

#endif
