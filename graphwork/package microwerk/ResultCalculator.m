result=zeros(16,30);
Img=imread('12.png');
I=rgb2gray(Img);
wallcolor=I(1,1);
% imshow(Img)
% imshow(I)
for j=1:16
   for i=1:30
        x=(i-1)*8+1;
        y=(j-1)*8+1;
        if (I(y,x+1) == wallcolor)
            result(j,i)=result(j,i)+8;
        end
        if (I(y+1,x+8) == wallcolor)
            result(j,i)=result(j,i)+1;
        end
        if (I(y+8,x+7) == wallcolor)
            result(j,i)=result(j,i)+2;
        end
        if (I(y+7,x) == wallcolor)
            result(j,i)=result(j,i)+4;
        end     
   end
% result(i,j)
% pause
end
result=dec2hex(result)
csvwrite('result_12_matrix.csv',result)