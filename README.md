
## RecyclerRefreshLayout
RecyclerViewItemDecoration is an Android library that allows developers to easily create RecyclerView with ItemDecoration.
Besides, the library will have new ItemDecoration added constantly.

![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/PinnedHeaderItemDecoration.gif?width=300)
![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/LinearDividerItemDecoration.gif?width=300)
![](https://github.com/dinuscxj/RecyclerItemDecoration/blob/master/Preview/GridOffsetsItemDecoration.gif?width=300)

## Features
 * Support the ItemView with the specific ViewType sticky on the header of the RecyclerView.
 * Support custom different Divider or Offsets , when using RecyclerView with different ViewType.

## Usage
 Set HeaderItemDecoration<br/>
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

 Set LinearDividerItemDecoration<br/>
 ```java
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

 Set LinearDividerItemDecoration<br/>
 ```java
 LinearOffsetItemDecoration dividerItemDecoration = new LinearOffsetItemDecoration(
                     LinearOffsetItemDecoration.LINEAR_OFFSETS_HORIZONTAL);
 dividerItemDecoration.registerTypeOffset(getItemViewType(ItemAnimal.class),
     new LinearOffsetItemDecoration.OffsetsCreator() {
         @Override
         public int create(RecyclerView parent, int adapterPosition) {
             return 30;
         }
     }
 );
 ```

 Set GridDividerItemDecoration<br/>
 ```java
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

 Set GridOffsetsItemDecoration<br/>
 ```java
 GridOffsetsItemDecoration dividerItemDecoration = new GridOffsetsItemDecoration(
                     GridOffsetsItemDecoration.GRID_OFFSETS_HORIZONTAL);
 dividerItemDecoration.registerTypeDrawable(getItemViewType(ItemAnimal.class),
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
