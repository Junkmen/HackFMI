#ifndef _POINT_HPP_
#define _POINT_HPP_
#include <cmath>

using std::abs;

class Point{
    int x_, y_;
public:
    Point() : x_(0), y_(0){}
    Point(int x, int y) : x_(x), y_(y){}
    int x() const {return x_;}
    int y() const {return y_;}
    int set_x(int x){x_ = x;}
    void set_y(int y){y_ = y;}

    Point operator+(const Point p){return Point(x_ + p.x_, y_ + p.y_);}
    Point operator-(const Point p){return Point(x_ - p.x_, y_ - p.y_);}
    Point& operator+=(const Point& p){x_ += p.x_; y_ += p.y_; return *this;}
    Point& operator-=(const Point& p){x_ -= p.x_; y_ -= p.y_; return *this;}
    Point& operator=(const Point p){x_ = p.x_; y_ = p.y_; return *this;}
    bool operator==(const Point p){return (x_ == p.x_ && y_ == p.y_);}
    bool operator!=(const Point& p){return !(*this == p);}
};

#endif // _POINT_HPP_
