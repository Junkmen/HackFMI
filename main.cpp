#include <iostream>
using namespace std;
#include "GameObject.hpp"

int main(){
    SDL_Window* window = (SDL_Window*)nullptr;
    SDL_Renderer* renderer = (SDL_Renderer*)nullptr;
    SDL_CreateWindowAndRenderer(0, 0, SDL_WINDOW_FULLSCREEN_DESKTOP, &window, &renderer);
    bool running = false;
    if(window != (SDL_Window*)nullptr && renderer != (SDL_Renderer*)nullptr){
        running = true;
    }
    SDL_Event event;
    GameObject obj(25, 25, "a.jpg");
    obj.load_texture(renderer, "a.jpg");
    obj.set_width(250);
    obj.set_height(250);
    
    while(running){
        if(SDL_PollEvent(&event)){
            if(event.key.keysym.scancode == SDL_SCANCODE_ESCAPE)
                running = false;
        }
        SDL_RenderClear(renderer);
        obj.draw(renderer);
        obj.set_x(rand() % 1430);
        obj.set_y(rand() % 500);
        SDL_RenderPresent(renderer);
    };
    return 0;
};
