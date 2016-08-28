
## RecyclerItemDecoration
RecyclerViewItemDecoration is an Android library that allows developers to easily create RecyclerView with ItemDecoration.
Besides, the library will have new ItemDecoration added constantly.

![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/PinnedHeaderItemDecoration.gif?width=300)
![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/ShaderItemDecoration.gif?width=300)<br/>
![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/LinearDividerItemDecoration.gif?width=300)
![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/GridOffsetsItemDecoration.gif?width=300)

## Features
 * Support custom top shader or bottom shader to the RecyclerView.
 * Support the ItemView with the specific ViewType sticky on the header of the RecyclerView.
 * Support custom different Divider or Offsets , when using RecyclerView with different ViewType.

## Usage
 Add dependency
 ```gradle
 dependencies {
    compile 'com.dinuscxj:recycleritemdecoration:1.0.0'
 }
 ```

 Set HeaderItemDecoration
 ```java
 PinnedHeaderDecoration pinnedHeaderDecoration = new PinnedHeaderDecoration();
 pinnedHeaderDecoration.registerTypePinnedHeader(getItemViewType(ItemTitle.class),
     new PinnedHeaderDecoration.PinnedHeaderCreator() {
         @Override
         public boolean create(RecyclerView parent, int adapterPosition) {
             return true;
         }
     }
 );
 ```

 SetShaderItemDecoration
 ```java
 //simple usage
 ShaderItemDecoration shaderItemDecoration = new ShaderItemDecoration(getActivity(),
                     ShaderItemDecoration.SHADER_BOTTOM
                     | ShaderItemDecoration.SHADER_TOP
                     | ShaderItemDecoration.SHADER_RIGHT
                     | ShaderItemDecoration.SHADER_LEFT);
 shaderItemDecoration.setShaderTopDistance(shaderTopDistance);
 shaderItemDecoration.setShaderBottomDistance(shaderBottomDistance);
 //complex usage
 ShaderItemDecoration shaderItemDecoration = new ShaderItemDecoration(getActivity(),
                     ShaderItemDecoration.SHADER_BOTTOM | ShaderItemDecoration.SHADER_TOP);
 shaderItemDecoration.registerBottomShaderCreator(new ShaderItemDecoration.ShaderCreator() {
     @Override
     public Shader createShader(RecyclerView parent) {
         return null;
     }
 });
 ```

 Set LinearDividerItemDecoration
 ```java
 //simple usage
 LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                     getActivity(), LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
 dividerItemDecoration.setDivider(divider);
 //complex usage
 LinearDividerItemDecoration dividerItemDecoration = new LinearDividerItemDecoration(
                     getActivity(), LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL);
 dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
     new LinearDividerItemDecoration.DrawableCreator() {
         @Override
         public Drawable create(RecyclerView parent, int adapterPosition) {
             return getResources().getDrawable(R.drawable.bg_animal_divider);
         }
     }
 );
 ```

 Set LinearOffsetsItemDecoration
 ```java
 //simple usage
 LinearOffsetsItemDecoration offsetsItemDecoration = new LinearOffsetItemDecoration(
                       LinearOffsetItemDecoration.LINEAR_OFFSETS_HORIZONTAL);
 offsetsItemDecoration.setItemOffsets(itemOffsets);
 //complex usage
 LinearOffsetsItemDecoration offsetsItemDecoration = new LinearOffsetItemDecoration(
                     LinearOffsetItemDecoration.LINEAR_OFFSETS_HORIZONTAL);
 offsetsItemDecoration.registerTypeOffset(getItemViewType(ItemAnimal.class),
     new LinearOffsetItemDecoration.OffsetsCreator() {
         @Override
         public int create(RecyclerView parent, int adapterPosition) {
             return 30;
         }
     }
 );
 offsetsItemDecoration.setOffsetEdge(true);
 offsetsItemDecoration.setOffsetLast(true);
 ```

 Set GridDividerItemDecoration
 ```java
 //simple usage
 GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(getActivity(),
                     GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
 dividerItemDecoration.setVerticalDivider(verticalDivider);
 dividerItemDecoration.setHorizontalDivider(horizontalDivider);
 //complex usage
 GridDividerItemDecoration dividerItemDecoration = new GridDividerItemDecoration(getActivity(),
                     GridDividerItemDecoration.GRID_DIVIDER_VERTICAL);
 dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
     new GridDividerItemDecoration.DrawableCreator() {
         @Override
         public Drawable createVertical(RecyclerView parent, int adapterPosition) {
             return getResources().getDrawable(R.drawable.bg_animal_divider);
         }
         @Override
         public Drawable createHorizontal(RecyclerView parent, int adapterPosition) {
             return getResources().getDrawable(R.drawable.bg_animal_divider);
         }
     }
 );
 ```

 Set GridOffsetsItemDecoration
 ```java
 //simple usage
 GridOffsetsItemDecoration offsetsItemDecoration = new GridOffsetsItemDecoration(
                     GridOffsetsItemDecoration.GRID_OFFSETS_HORIZONTAL);
 offsetsItemDecoration.setVerticalItemOffsets(verticalItemOffsets);
 offsetsItemDecoration.setHorizontalItemOffsets(horizontalItemOffsets);
 //complex usage
 GridOffsetsItemDecoration offsetsItemDecoration = new GridOffsetsItemDecoration(
                     GridOffsetsItemDecoration.GRID_OFFSETS_HORIZONTAL);
 offsetsItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
     new GridOffsetsItemDecoration.OffsetsCreator() {
         @Override
         public int createVertical(RecyclerView parent, int adapterPosition) {
             return 30;
         }
         @Override
         public int createHorizontal(RecyclerView parent, int adapterPosition) {
             return 30;
         }
     }
 );
 offsetsItemDecoration.setOffsetEdge(true);
 offsetsItemDecoration.setOffsetLast(true);
 ```
## Learn More About RecyclerView
* [PullZoomRecyclerView](https://github.com/dinuscxj/PullZoomRecyclerView)
* [RecyclerRefreshLayout](https://github.com/dinuscxj/RecyclerRefreshLayout)

## License
    Copyright 2015-2019 dinus

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
